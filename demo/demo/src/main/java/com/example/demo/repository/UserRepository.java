package com.example.demo.repository;

import com.example.demo.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query( value = """
            select * 
                from users 
                    where email = :email
    """, nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Modifying
    @Transactional
    @Query(value = """
        insert into users (id,name, email, password)
        values (:id, :name, :email, :password)
""", nativeQuery = true)
    void insertUser(
            @Param("id") UUID id,
            @Param("name") String name,
            @Param("email") String email,
            @Param("password") String password
    );

    @Query( value = """
            select * 
                from users 
    """, nativeQuery = true)
    List<User> getAllUsers();

    @Query( value = """
            select *
                from users 
                    where id = :id
    """, nativeQuery = true)
    Optional<User> findUserById(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query( value = """
            UPDATE users
                set
                    name = :name
                where id=:id
    """, nativeQuery = true)
    int UpdateUserName(@Param("id") UUID id,
                                    @Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = """
    DELETE FROM users
    WHERE id = :id
    """, nativeQuery = true)
    int deleteUserById(@Param("id") UUID id);
}