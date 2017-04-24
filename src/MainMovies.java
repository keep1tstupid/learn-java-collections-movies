import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Sasha on 24.04.2017.
 */

public class MainMovies {
    public static void main(String[] args){
        List<String> moviesList = new ArrayList<>();
            moviesList.add("The Shawshank Redemption");
            moviesList.add("The Green Mile");
            moviesList.add("Forrest Gump");

        Map<String, Integer> moviesRaitingMap = new HashMap<String, Integer>();
        File savedRating = new File("SavedRating.txt");

        for (String movie : moviesList){
            Scanner s = new Scanner(System.in);
            System.out.println("Enter you rating for " + movie + " please");
            int rating = s.nextInt();
            moviesRaitingMap.put(movie, rating);
        }

        String result = moviesRaitingMap
                .keySet()
                .stream()
                .map((movie) ->  movie + " has rating " + moviesRaitingMap.get(movie) + System.getProperty("line.separator"))
                .collect(Collectors.joining());

        try (FileWriter fw = new FileWriter(savedRating);
             BufferedWriter bw = new BufferedWriter(fw)){
            bw.write(result);
            bw.close();
        } catch (IOException ex) {
            System.out.println("We've got a problem");
        }

        System.out.println(moviesRaitingMap.toString());

        try (FileReader fr = new FileReader("SavedRating.txt");
            BufferedReader br = new BufferedReader(fr)) {
            Scanner s = new Scanner(System.in);
            System.out.println("What film are you looking for?");
            String enteredVal = s.next();
            Scanner r = new Scanner(br);
            r.useDelimiter(System.getProperty("line.separator"));

            while (r.hasNext()) {
                String tmp = r.next();
                if (tmp.contains(enteredVal)) {
                    System.out.println(tmp);
                }
            }
        } catch (IOException ex) {
            System.out.println("We've got a problem");
        }
    }
}