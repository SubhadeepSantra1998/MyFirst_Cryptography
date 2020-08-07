package com.androsubha.myfirst_cryptography;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Decryption extends AppCompatActivity {

    EditText password,decValue;
    TextView message;
    Button btnDecrypt;

    String outputString;
    String AES="AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);

        UIElements();


//when Decrypt button is clicked
        btnDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    outputString=decrypt(decValue.getText().toString(),password.getText().toString());
                    message.setText(outputString);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    Toast.makeText(getApplicationContext(),"Wrong Password",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    Toast.makeText(getApplicationContext(),"Wrong Password", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });


    }

    //decryption process
    private String decrypt(String data, String password) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec key=generateKey(password);
        Cipher c=Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE,key);
        byte[] decodedVal= Base64.decode(data,Base64.DEFAULT);
        byte[] decValue=c.doFinal(decodedVal);
        String decryptedValue=new String(decValue);
        return  decryptedValue;
    }

    //generate method call
    private SecretKeySpec generateKey(String password) throws IOException, NoSuchAlgorithmException {
        final MessageDigest digest=MessageDigest.getInstance("SHA-256");
        byte[] bytes=password.getBytes("UTF-8");
        digest.update(bytes,0,bytes.length);
        byte[] key=digest.digest();
        SecretKeySpec secretKeySpec=new SecretKeySpec(key,"AES");
        return secretKeySpec;
    }



    private void UIElements() {
        password=findViewById(R.id.editTextTextPassword);
        decValue=findViewById(R.id.editTextTextPersonName);
        btnDecrypt=findViewById(R.id.button);
        message=findViewById(R.id.textView);
    }


}