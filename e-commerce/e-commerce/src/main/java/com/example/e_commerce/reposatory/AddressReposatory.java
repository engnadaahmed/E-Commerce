package com.example.e_commerce.reposatory;

import com.example.e_commerce.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressReposatory  extends JpaRepository<Address,Long> {
}
