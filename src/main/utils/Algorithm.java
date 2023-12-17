package main.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Utility class containing various static methods for common algorithms and operations on collections.
 *
 * @author Dimas Dermawan
 */
public class Algorithm {
    private Algorithm() {}

    /**
     * Paginate through an array, iterable, or iterator based on the provided criteria.
     *
     * @param array     The array to paginate.
     * @param page      The page number.
     * @param pageSize  The number of elements per page.
     * @param pred      The predicate to filter elements.
     * @param <T>       The type of elements.
     * @return A paginated list of elements.
     */
    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return paginate(it, page, pageSize, pred);
    }

    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return paginate(it, page, pageSize, pred);
    }

    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred) {
        List<T> temp = new ArrayList<>();

        int numPerPage = 5;

        int startingIndex = page * numPerPage;
        int lastIndex = startingIndex + pageSize;
        int index = 0;

        while (iterator.hasNext()) {
            T current = iterator.next();

            if (pred.predicate(current) && index >= startingIndex && index < lastIndex)
                temp.add(current);

            index++;
        }

        return temp;
    }

    /**
     * Count occurrences of a value in an array, iterable, or iterator.
     *
     * @param array The array to search.
     * @param value The value to count.
     * @param <T>   The type of elements.
     * @return The number of occurrences of the value.
     */
    public static <T> int count(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, value);
    }

    /**
     * Count elements satisfying a predicate in an array, iterable, or iterator.
     *
     * @param array The array to search.
     * @param pred  The predicate to check.
     * @param <T>   The type of elements.
     * @return The number of elements satisfying the predicate.
     */
    public static <T> int count(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return count(it, pred);
    }

    public static <T> int count(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return count(it, value);
    }

    public static <T> int count(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return count(iterator, pred);
    }

    public static <T> int count(Iterator<T> iterator, Predicate<T> pred) {
        int counter = 0;

        while (iterator.hasNext()) {
            T current = iterator.next();

            if (pred.predicate(current)) {
                counter++;
            }
        }

        return counter;
    }

    public static <T> int count(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return count(it, pred);
    }

    /**
     * Check if a value exists in an array, iterable, or iterator.
     *
     * @param array The array to search.
     * @param value The value to check for existence.
     * @param <T>   The type of elements.
     * @return True if the value exists; otherwise, false.
     */
    public static <T> boolean exists(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, value);
    }

    public static <T> boolean exists(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return exists(iterator, pred);
    }

    public static <T> boolean exists(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return exists(it, pred);
    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {
            T current = iterator.next();
            if (pred.predicate(current))
                return true;
        }
        return false;
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return exists(it, pred);
    }

    /**
     * Check if a value exists in an array, iterable, or iterator.
     *
     * @param array The array to search.
     * @param value The value to check for existence.
     * @param <T>   The type of elements.
     * @return True if the value exists; otherwise, false.
     */
    public static <T> T find(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, value);
    }

    /**
     * Find the first occurrence of an element satisfying a predicate in an array, iterable, or iterator.
     *
     * @param array The array to search.
     * @param pred  The predicate to check.
     * @param <T>   The type of elements.
     * @return The first element satisfying the predicate, or null if not found.
     */
    public static <T> T find(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return find(it, pred);
    }

    public static <T> T find(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return find(it, value);
    }

    public static <T> T find(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return find(iterator, pred);
    }

    public static <T> T find(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return find(it, pred);
    }

    public static <T> T find(Iterator<T> iterator, Predicate<T> pred) {
        while (iterator.hasNext()) {
            T current = iterator.next();

            if (pred.predicate(current))
                return current;
        }

        return null;
    }

    /**
     * Collect elements equal to a value from an array, iterable, or iterator into a list.
     *
     * @param array The array to search.
     * @param value The value to collect.
     * @param <T>   The type of elements.
     * @return A list containing elements equal to the value.
     */
    public static <T> List<T> collect(T[] array, T value) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, value);
    }

    /**
     * Collect elements satisfying a predicate from an array, iterable, or iterator into a list.
     *
     * @param array The array to search.
     * @param pred  The predicate to check.
     * @param <T>   The type of elements.
     * @return A list containing elements satisfying the predicate.
     */
    public static <T> List<T> collect(T[] array, Predicate<T> pred) {
        final Iterator<T> it = Arrays.stream(array).iterator();
        return collect(it, pred);
    }

    public static <T> List<T> collect(Iterable<T> iterable, T value) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, value);
    }

    public static <T> List<T> collect(Iterator<T> iterator, T value) {
        final Predicate<T> pred = value::equals;
        return collect(iterator, pred);
    }

    public static <T> List<T> collect(Iterable<T> iterable, Predicate<T> pred) {
        final Iterator<T> it = iterable.iterator();
        return collect(it, pred);
    }

    public static <T> List<T> collect(Iterator<T> iterator, Predicate<T> pred) {
        List<T> temp = new ArrayList<T>();

        while (iterator.hasNext()) {
            T current = iterator.next();

            if (pred.predicate(current))
                temp.add(current);
        }

        return temp;
    }

    /**
     * Hash a password using the MD5 algorithm.
     *
     * @param password The password to hash.
     * @return The hashed password.
     */
    public static String hashPassword(String password) {
        String hashedPass = password;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(hashedPass.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            hashedPass = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return hashedPass;
    }
}
