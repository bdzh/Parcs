import java.io.Serializable;
import parcs.*;

public class Vernam implements AM {

    private final static char encryptionArr[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    @Override
    public void run(AMInfo info){

        String text = (String) info.parent.readObject();

        String key = generateKey(text.length());
        String encryptedString = encrypt(text,key);

        info.parent.write((Serializable) encryptedString);
    }

    public String encrypt(String plainText, String key){
		
        if(plainText.length() != key.length()){
            return null;
        }

        byte[] plainTextBytes = plainText.getBytes();
        byte[] keyBytes = key.getBytes();
        byte[] encryptedText = new byte[plainTextBytes.length];
        for(int i = 0; i < plainTextBytes.length; i++){
            encryptedText[i] = (byte) (keyBytes[i] ^ plainTextBytes[i]);
        }

        return new String(encryptedText);
    }

    public String generateKey(int length){
		
        if(length <= 0){
            return null;
        }
        String key = "";

        for(int i = 0; i < length; i++){
            int randomValue = ((int) Math.random())%26;
            key += encryptionArr[randomValue];
        }
        return key;
    }

}
