package net.badbird5907.decoder;

import com.google.common.io.LittleEndianDataInputStream;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

@Getter
@Setter
public class NBSFile {
    private short length,height;
    private String name,author,origAuthor,desc;
    private short tempo;
    private byte autosave,autosaveDuration,timeSig;
    private int minsSpent,leftClicks,rightClicks,blocksAdded,blocksRemoved;
    //private String schemName;

    private LittleEndianDataInputStream inputStream;
    @SneakyThrows
    public NBSFile(InputStream is){
        inputStream = new LittleEndianDataInputStream(is);
        for (Field declaredField : this.getClass().getDeclaredFields()) {
            System.out.println("Field: " + declaredField.getName() + " | " + declaredField.getType().getName());
            if (declaredField.getType().isAssignableFrom(short.class))
                declaredField.set(this,inputStream.readShort());
            else if (declaredField.getType().isAssignableFrom(String.class))
                try{
                    declaredField.set(this,inputStream.readUTF());
                } catch (Exception e) {
                    System.err.println("Failed to read string for " + declaredField.getName() + ", default to \"unknown\"");
                    declaredField.set(this,"Unknown");
                }
            else if (declaredField.getType().isAssignableFrom(byte.class))
                declaredField.set(this,inputStream.readByte());
            else if (declaredField.getType().isAssignableFrom(int.class))
                declaredField.set(this,inputStream.readInt());
            System.out.println(declaredField.get(this));
        }
        System.out.println("Length: " + length);
        System.out.println("Name: " + name);
        System.out.println("Tempo: " + tempo);
    }
}
