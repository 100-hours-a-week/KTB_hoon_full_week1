package repository;

import content.Content;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ContentRepository {

    private final Map<Integer, Content> contents = new HashMap<>();

    public void add(Content content) {
        contents.put(content.getId(), content);
    }

    public Optional<Content> get(int id) {
        return Optional.of(contents.get(id));
    }

    public List<Content> getAll() {
        return new ArrayList<>(contents.values());
    }
}
