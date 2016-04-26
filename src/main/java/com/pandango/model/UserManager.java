/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pandango.model;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.FirebaseException;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sara
 */
public class UserManager {
    private Firebase ref = new Firebase("https://burning-inferno-8355.firebaseio.com/");
    private static List<String> banned = new ArrayList<>();
    private static List<String> locked = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private static User currentUser;
    private boolean loggedIn;
    private static String userRef;
    
    public boolean loginUser(String email, String password) {
        ref.authWithPassword(email, password,
            new Firebase.AuthResultHandler() {
                @Override
                public void onAuthenticated(AuthData authData) { 
                    System.out.println("here");
                     // Authentication just completed successfully :
                    ref.child("users").child(authData.getUid()).child("loginAttempts").setValue((Integer) 0);
                    userRef = authData.getUid();
                    ref.child("users").child(authData.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            currentUser = snapshot.getValue(User.class);
                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {
                        }
                    });
                    loggedIn = true;
                }
                @Override
                public void onAuthenticationError(FirebaseError error) {
                    // Something went wrong :(
    //                    switch (error.getCode()) {
    //                        case FirebaseError.USER_DOES_NOT_EXIST:
    //                            // handle a non existing user
    //                            break;
                    Firebase checkRef = ref.child("users");
                    Query query = checkRef.orderByChild("email").equalTo(email);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot ds) {
                            for (DataSnapshot dataSnap : ds.getChildren()) {
                                currentUser = dataSnap.getValue(User.class);
                                currentUser.setLoginAttempts(currentUser.getLoginAttempts() + 1);
                                userRef = dataSnap.getRef().toString();
                                dataSnap.getRef().child("loginAttempts").setValue((Integer) currentUser.getLoginAttempts());
                            }
                            loggedIn = false;
                        }

                        @Override
                        public void onCancelled(FirebaseError fe) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    });
                }
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return loggedIn;
                    
    }
    
    public void logoutUser() {
        ref.unauth();
        System.out.println(ref.getAuth());
    }
    public void addUser(String email, String password, User user) {
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
    public void removeUser(String email, String password) {
        Firebase removeRef = ref.child("users");
        removeRef.removeUser(email, password, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onError(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Query removeQuery = removeRef.orderByChild("email").equalTo(email);
        removeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot dataSnap : ds.getChildren()) {
                    Firebase removeRef = dataSnap.getRef();
                    removeRef.removeValue();
                }
            }

            @Override
            public void onCancelled(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void banUser(String email, User user) {
        Firebase bannedRef = ref.child("bannedUsers");
        bannedRef.push().setValue(user);
        Query removeQuery = ref.child("users").orderByChild("email").equalTo(email);
        removeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
//                System.out.println(ds.getChildren());
//                removeRef.removeValue();
                for (DataSnapshot dataSnap : ds.getChildren()) {
                    Firebase removeRef = dataSnap.getRef();
                    removeRef.removeValue();
                }
            }

            @Override
            public void onCancelled(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void lockUser(User user) {
        Firebase loginRef = new Firebase("https://burning-inferno-8355.firebaseio.com/locked");
        loginRef.push().setValue(user);
    } 
    
    public List<String> checkForBan(String email) {
        Firebase bannedRef = ref.child("bannedUsers");
        Query bannedQuery = bannedRef.orderByChild("email");
        bannedQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot dataSnap : ds.getChildren()) {
                    User user = dataSnap.getValue(User.class);
                    banned.add(user.getEmail());
                }
            }

            @Override
            public void onCancelled(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        return banned;
    }
    
    public List<String> checkForLocked(String email) {
        Firebase lockedRef = ref.child("locked");
        Query lockedQuery = lockedRef.orderByChild("email");
        lockedQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot dataSnap : ds.getChildren()) {
                    User user = dataSnap.getValue(User.class);
                    locked.add(user.getEmail());
                }
            }

            @Override
            public void onCancelled(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        return locked;
    }
    
    public void unlockUser(String email) {
        Firebase removeRef = ref.child("locked");
        Query removeQuery = removeRef.orderByChild("email").equalTo(email);
        removeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot dataSnap : ds.getChildren()) {
                    Firebase removeRef = dataSnap.getRef();
                    removeRef.removeValue();
                }
            }

            @Override
            public void onCancelled(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        Firebase usersRef = ref.child("users");
        Query usersQuery = usersRef.orderByChild("email");
        usersQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot dataSnap : ds.getChildren()) {
                    dataSnap.getRef().child("loginAttempts").setValue(0);
                }
            }

            @Override
            public void onCancelled(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void getLoginAttempts(String email) throws FirebaseException{
        Firebase checkRef = ref.child("users");
        Query query = checkRef.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot dataSnap : ds.getChildren()) {
                    User user = dataSnap.getValue(User.class);
                    user.setLoginAttempts(user.getLoginAttempts() + 1);
                    dataSnap.getRef().child("loginAttempts").setValue((Integer) user.getLoginAttempts());
                    if (user.getLoginAttempts() >= 3) {
                        Firebase ref = new Firebase("https://burning-inferno-8355.firebaseio.com/locked");
                        ref.push().setValue(user);
                        throw new FirebaseError(FirebaseError.MAX_RETRIES, "Used 3 login attempts").toException();
                    } else {
                        throw new FirebaseError(FirebaseError.INVALID_PASSWORD, "You have used " + user.getLoginAttempts() + " login attempts. You have three until your account is locked.").toException();
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public void addMovie(Movie movie, double rating) {
        if (currentUser.getMovieList() == null || !currentUser.getMovieList().containsKey(movie.toString())) {
            currentUser.getMovieList().put(movie.toString(), rating);
            ref.child("users").child(userRef).child("movieList").setValue(currentUser.getMovieList());
        }
    }
    
    public double getRating(Movie movie) {
        if (currentUser.getMovieList() != null && currentUser.getMovieList().containsKey(movie.toString())) {
            return currentUser.getMovieList().get(movie.toString());
        }
        return -1;
    }
    
    public List<User> getUsers() {
            Firebase usersRef = ref.child("users");
            Query bannedQuery = usersRef.orderByChild("email");
            bannedQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot ds) {
                    for (DataSnapshot dataSnap : ds.getChildren()) {
                        User user = dataSnap.getValue(User.class);
                        users.add(user);
                    }
                    System.out.println("-" + users.size());
                }

                @Override
                public void onCancelled(FirebaseError fe) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        System.out.println("-" + users.size());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    
    public void promoteUser(String email) {
        Firebase usersRef = ref.child("users");
        Query bannedQuery = usersRef.orderByChild("email");
        bannedQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot ds) {
                for (DataSnapshot dataSnap : ds.getChildren()) {
                    dataSnap.getRef().child("admin").setValue(1);

                }
            }

            @Override
            public void onCancelled(FirebaseError fe) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    public User getUser() {
        return currentUser;
    }
    
    public void saveProfile(User user) {
        ref.child("users").child(userRef).child("major").setValue(user.getMajor());
        ref.child("users").child(userRef).child("bio").setValue(user.getBio());
        
    }
    
    public void forgotPassword(String email) {
        ref.resetPassword(email, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // password reset email sent
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        });
    }
    
    public void changePassword(String email, String oldPassword, String newPassword) {
        ref.changePassword(email, oldPassword, newPassword, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // password changed
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        });
    }
    
    public void changeEmail(String oldEmail, String password, String newEmail) {
        ref.changeEmail(oldEmail, password, newEmail, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                // email changed
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                // error encountered
            }
        });
    }
}
