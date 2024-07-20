package com.example.e_commerce.services;

import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.entity.CartItem;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.CartItemException;
import com.example.e_commerce.exceptions.UserException;
import com.example.e_commerce.reposatory.CartItemRepository;
import com.example.e_commerce.reposatory.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImplementation implements CartItemService {

    private CartItemRepository cartItemRepository;
    private ProductService productService;
    private CartRepository cartRepository;
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(CartItemServiceImplementation.class);
public CartItemServiceImplementation(CartItemRepository cartItemRepository,ProductService productService,CartRepository cartRepository,UserService userService){

    this.cartItemRepository=cartItemRepository;
    this.productService=productService;
    this.cartRepository=cartRepository;
    this.userService=userService;
}
    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        CartItem createdCartItem = cartItemRepository.save(cartItem);
        return createdCartItem;
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item=findCartItemById(id);
        User user = userService.findUserById(item.getUserId());
        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()* item.getQuantity());

        }
        return  cartItemRepository.save(item);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        logger.info("Attempting to remove cart item with ID: {} for user with ID: {}", cartItemId, userId);
        CartItem cartItem = findCartItemById(cartItemId);
        logger.info("Cart item found: {}", cartItem);

        User user = userService.findUserById(cartItem.getUserId());
        logger.info("User associated with cart item: {}", user);
        User reqUser = userService.findUserById(userId);
        logger.info("Requesting user: {}", reqUser);
        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
            logger.info("Cart item with ID: {} deleted", cartItemId);
        }else {
            logger.warn("User with ID: {} is not authorized to delete cart item with ID: {}", userId, cartItemId);
            throw new UserException("User is not authorized to delete this cart item");
        }


    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) throws CartItemException {
        CartItem cartItem=cartItemRepository.isCartItemExist(cart,product,size,userId);
        return cartItem;
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);
        if (opt.isPresent()) {
            return opt.get();
        }else {
            throw new CartItemException("CartItem not found"+cartItemId);
        }
    }
}
