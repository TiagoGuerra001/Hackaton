package com.example.demo.repository;

import com.example.demo.entity.MainPart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 * Created by IntelliJ IDEA.
 * Project : springboot-adminlte3-template
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/15/22
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public interface MainPartRepository extends JpaRepository<MainPart, Long> {
    Optional<MainPart> findByName(String name);
}
