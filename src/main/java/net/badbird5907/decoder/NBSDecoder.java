package net.badbird5907.decoder;

import com.google.common.io.LittleEndianDataInputStream;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileInputStream;

public class NBSDecoder {
    @SneakyThrows
    public static void main(String[] args) {
        File file = new File("test1.nbs");
        new NBSFile(new FileInputStream(file));
    }
}
