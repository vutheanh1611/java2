import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
        private static final String BOOK_FILE_NAME_FILE_NAME = "book.dat";

        public void write(List<Book> BookList) {
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;
            try {
                fos = new FileOutputStream(new File(BOOK_FILE_NAME_FILE_NAME));
                oos = new ObjectOutputStream(fos);
                oos.writeObject(BookList);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                closeStream(fos);
                closeStream(oos);
            }
        }

        public List<Book> read() {
            List<Book> BookList = new ArrayList<>();
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(new File(BOOK_FILE_NAME_FILE_NAME));
                ois = new ObjectInputStream(fis);
                BookList = (List<Book>) ois.readObject();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                closeStream(fis);
                closeStream(ois);
            }
            return BookList;
        }

        private void closeStream(InputStream is) {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void closeStream(OutputStream os) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

}
