package com.example.mobilecurd.service;
 
import java.util.List;
import java.util.Optional;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import com.example.mobilecurd.model.Mobile;
import com.example.mobilecurd.repository.MobileRepository;
 
@Service
public class MobileService {
 
    @Autowired
    private MobileRepository mobileRepository;
 
    public Mobile saveMobile(Mobile mobile) {
        return mobileRepository.save(mobile);
    }
 
    public List<Mobile> getAllMobiles() {
        return mobileRepository.findAll();
    }
 
    public Optional<Mobile> getMobileById(int id) {
        return mobileRepository.findById(id);
    }
 
    public Mobile updateMobile(int id, Mobile mobile) {
        mobile.setId(id);
        return mobileRepository.save(mobile);
    }
 
    public void deleteMobile(int id) {
        mobileRepository.deleteById(id);
    }
}