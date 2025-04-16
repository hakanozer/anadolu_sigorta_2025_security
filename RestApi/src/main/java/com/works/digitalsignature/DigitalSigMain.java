package com.works.digitalsignature;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONObject;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class DigitalSigMain {

    static {
        // BouncyCastle Provider ekleniyor
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String spec = "secp256k1";
    private static final String algo = "SHA256withECDSA";

    // sender
    public JSONObject sender() throws Exception {
        JSONObject obj = new JSONObject();

        ECGenParameterSpec ecSpec = new ECGenParameterSpec(spec);
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC", "BC");
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String plainText = "security data";
        Map<String, Object> hm = new HashMap<>();
        hm.put("status", true);
        hm.put("name", "Ali Bilmem");
        JSONObject objHm = new JSONObject(hm);

        Signature signature = Signature.getInstance(algo, "BC");
        signature.initSign(privateKey);
        signature.update(plainText.getBytes("UTF-8"));
        byte[] signBytes = signature.sign();

        String pub = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String sig = Base64.getEncoder().encodeToString(signBytes);

        obj.put("publicKey", pub);
        obj.put("signature", sig);
        obj.put("message", plainText);
        obj.put("algo", algo);
        obj.put("datas", objHm);

        System.out.println(obj);

        return obj;
    }

    public boolean receiver(JSONObject obj) throws Exception {
        Signature signature = Signature.getInstance(obj.getString("algo"), "BC");
        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");

        byte[] pubKeyBytes = Base64.getDecoder().decode(obj.getString("publicKey"));
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        signature.initVerify(publicKey);
        signature.update(obj.getString("message").getBytes("UTF-8"));

        boolean status = signature.verify(Base64.getDecoder().decode(obj.getString("signature")));

        if (status) {
            JSONObject ob = obj.getJSONObject("datas");
            System.out.println(ob);
        } else {
            System.err.println("Data or Signature Fail");
        }

        return status;
    }

    public static void main(String[] args) throws Exception {
        DigitalSigMain sigMain = new DigitalSigMain();
        sigMain.receiver(sigMain.sender());
    }
}
