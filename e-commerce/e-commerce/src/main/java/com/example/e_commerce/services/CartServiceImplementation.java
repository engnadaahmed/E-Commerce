package com.example.e_commerce.services;

import com.example.e_commerce.entity.Cart;
import com.example.e_commerce.entity.CartItem;
import com.example.e_commerce.entity.Product;
import com.example.e_commerce.entity.User;
import com.example.e_commerce.exceptions.CartItemException;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.reposatory.CartItemRepository;
import com.example.e_commerce.reposatory.CartRepository;
import com.example.e_commerce.request.AddItemRequest;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImplementation implements CartService{
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;
    private CartItemRepository cartItemRepository;

    public  CartServiceImplementation(CartRepository cartRepository,CartItemService cartItemService,ProductService productService,CartItemRepository cartItemRepository){

        this.cartItemRepository=cartItemRepository;
        this.productService=productService;
        this.cartItemService=cartItemService;
        this.cartRepository=cartRepository;
    }

    @Override
    public Cart createCart(User user) {
        Cart cart=new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws ProductException, CartItemException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(req.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
        if (isPresent == null) {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(req.getQuantity());
            cartItem.setUserId(userId);
            int price = req.getQuantity() * product.getDiscountedPrice();
           cartItem.setPrice(price);
           cartItem.setSize(req.getSize());
           CartItem createdCartItem=cartItemService.createCartItem(cartItem);
           cart.getCartItems().add(createdCartItem);
        }
        return "Item is added to the cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart =cartRepository.findByUserId(userId);
        int totalPrice=0;
        int totalDiscountPrice=0;
        int totalItem=0;
        for (CartItem cartItem : cart.getCartItems()) {
            totalPrice+=cartItem.getPrice();
            totalDiscountPrice+=cartItem.getDiscountedPrice();
            totalItem+=cartItem.getQuantity();
        }
        cart.setTotalDiscountedPrice(totalDiscountPrice);
        cart.setTotalItem(totalItem);
        cart.setTotalPrice(totalPrice);
        cart.setDiscount(totalPrice-totalDiscountPrice);


        return cartRepository.save(cart);
    }
}
