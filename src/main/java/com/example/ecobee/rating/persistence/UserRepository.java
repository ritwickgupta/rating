package com.example.ecobee.rating.persistence;

import com.example.ecobee.rating.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT count(id) FROM User u where u.country = :country and u.rValue <= (Select v.rValue from User v where v.firstName = :firstName and v.lastName = :lastName)")
    Integer findBetter(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("country") String country);

    @Query("SELECT count(id) FROM User u where u.country = :country")
    Integer findCount(@Param("country") String country);

    @Query("SELECT count(id) FROM User u where u.country = :country and u.province = :province and u.rValue <= (Select v.rValue from User v where v.firstName = :firstName and v.lastName = :lastName)")
    Integer findBetter(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("country") String country, @Param("province") String province);

    @Query("SELECT count(id) FROM User u where u.country = :country and u.province = :province")
    Integer findCount(@Param("country") String country, @Param("province") String province);

    @Query("SELECT count(id) FROM User u where u.country = :country and u.province = :province and u.city = :city and u.rValue <= (Select v.rValue from User v where v.firstName = :firstName and v.lastName = :lastName)")
    Integer findBetter(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("country") String country, @Param("province") String province, @Param("city") String city);

    @Query("SELECT count(id) FROM User u where u.country = :country and u.province = :province and u.city = :city")
    Integer findCount(@Param("country") String country, @Param("province") String province, @Param("city") String city);
}
