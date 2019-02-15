package my.spring.project.springmvc.config;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MappingJackson2JsonViewExt extends MappingJackson2JsonView {
    private static final Set<String> EXCLUDED_KEYS = new HashSet<>();

    public static void excludeModelKey(final String key) {
        EXCLUDED_KEYS.add(key);
    }

    @Override
    protected Object filterModel(final Map<String, Object> model) {
        final Map<String, Object> filteredModel = model.entrySet().stream()
                .filter(e -> {
                    final String key = e.getKey();
                    return !EXCLUDED_KEYS.contains(key);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return super.filterModel(filteredModel);
    }
}