/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango.model;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sara
 */
public class UserManager {
    private Firebase ref = new Firebase("https://burning-inferno-8355.firebaseio.com/");
    
    public void addUser(String email, String password, User user) {
        Query bannedQuery = ref.orderByChild("bannedUsers").orderByChild("email").equalTo(email);
        bannedQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                if (ds.getChildrenCount() > 0) {
                    throw new FirebaseError(FirebaseError.PERMISSION_DENIED, "The email entered is currently banned.").toException();
                }
            }

            @Override
            public void onCancelled(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                Firebase temp = ref.child("users").child("" + result.get("uid"));
                temp.setValue(user);
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
            }
            @Override
            public void onError(FirebaseError error) {
                switch (error.getCode()) {
                    case FirebaseError.EMAIL_TAKEN:
                        throw new FirebaseError(FirebaseError.EMAIL_TAKEN, "The email entered is already in use.").toException();
                }
            }
        });
    }
    public void removeUser(String email, User user) {
//        users.remove(email);
    }
    
    public void banUser(String email, User user) {
        
    }
}
