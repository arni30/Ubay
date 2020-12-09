package world.ucode.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class ImageHandler {

    public static void savePicture(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                System.out.println(file.getOriginalFilename());
                System.out.println(file.getContentType());
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(
                                new File("uploaded_" + file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                System.out.println("Вам не удалось загрузить " + file.getName() + " => " + e.getMessage());
            }
        } else {
            System.out.println("Вам не удалось загрузить " + file.getName() + " потому что файл пустой.");
        }
    }

    public static byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
}
