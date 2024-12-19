import java.io.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("files");
        File writer = new File("all.txt");

        try(
                BufferedWriter bw = new BufferedWriter(new FileWriter(writer))
                ) {
            if (file.isDirectory()) {
                readFile(file,bw);
            }else{
                System.out.println("File is not a directory");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void readFile(File file, BufferedWriter bw) throws IOException {
        File[] files = file.listFiles();
        if(files!=null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    readFile(f,bw);
                }else if (f.getName().endsWith(".txt")) {
                    readText(f,bw);
                }
            }
        }
    }

    private static void readText(File file, BufferedWriter bw) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new FileReader(file));
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Faylni o'qishda xatolik: " + file.getAbsolutePath());
        }
    }
}