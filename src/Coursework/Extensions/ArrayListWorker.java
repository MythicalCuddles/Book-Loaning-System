package Coursework.Extensions;

import Coursework.Objects.Book;
import Coursework.Objects.Fiction;
import Coursework.Objects.NonFiction;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class ArrayListWorker {
    public static int getIndexOfIdForBook(ArrayList<Book> arrayList, int bookId) {
        return IntStream.range(0, arrayList.size()).filter(i -> arrayList.get(i).getId() == bookId).findFirst().orElse(-1);
    }

    public static int getIndexOfIdForFiction(ArrayList<Fiction> arrayList, int bookId) {
        return IntStream.range(0, arrayList.size()).filter(i -> arrayList.get(i).getId() == bookId).findFirst().orElse(-1);
    }

    public static int getIndexOfIdForNonFiction(ArrayList<NonFiction> arrayList, int bookId) {
        return IntStream.range(0, arrayList.size()).filter(i -> arrayList.get(i).getId() == bookId).findFirst().orElse(-1);
    }
}
