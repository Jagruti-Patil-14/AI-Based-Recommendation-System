import java.util.*;

public class SimpleRecommendationSystem {

    // Sample data: user -> (item, rating)
    private static final Map<String, Map<String, Integer>> userRatings = new HashMap<>();

    public static void main(String[] args) {
        // Populate sample data
        userRatings.put("Alice", createMap(new String[]{"Item1", "Item2", "Item3"}, new int[]{5, 3, 4}));
        userRatings.put("Bob", createMap(new String[]{"Item1", "Item2", "Item3"}, new int[]{4, 5, 3}));
        userRatings.put("Charlie", createMap(new String[]{"Item1", "Item2", "Item4"}, new int[]{3, 4, 5}));

        // Recommend items for Alice
        String targetUser = "Alice";
        System.out.println("Recommendations for " + targetUser + ":");
        List<String> recommendations = getRecommendations(targetUser);
        recommendations.forEach(System.out::println);
    }

    private static List<String> getRecommendations(String user) {
        Map<String, Integer> targetUserRatings = userRatings.get(user);
        if (targetUserRatings == null) {
            return Collections.emptyList(); // Empty list if the user doesn't exist
        }

        Set<String> recommendedItems = new HashSet<>();

        // Find items rated by other users
        for (Map.Entry<String, Map<String, Integer>> entry : userRatings.entrySet()) {
            if (!entry.getKey().equals(user)) {
                Map<String, Integer> otherUserRatings = entry.getValue();
                for (String item : otherUserRatings.keySet()) {
                    if (!targetUserRatings.containsKey(item)) {
                        recommendedItems.add(item);
                    }
                }
            }
        }

        return new ArrayList<>(recommendedItems);
    }

    private static Map<String, Integer> createMap(String[] items, int[] ratings) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            map.put(items[i], ratings[i]);
        }
        return map;
    }
}
