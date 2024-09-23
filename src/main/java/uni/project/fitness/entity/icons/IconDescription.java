package uni.project.fitness.entity.icons;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum IconDescription {
    BODY("https://video2.topstretching-app.digitalfalcon.ae/assets/icons/body.png", "Flat belly, strong abs, tight buttocks"),
    IMMUNITY("https://video2.topstretching-app.digitalfalcon.ae/assets/icons/immunity.png", "Good immunity, endurance & strength"),
    RELIEF("https://video2.topstretching-app.digitalfalcon.ae/assets/icons/relief.png", "Beautiful relief, feminine arms & muscle tone"),
    IMMUNITY_2("https://video2.topstretching-app.digitalfalcon.ae/assets/icons/immunity_2.png", "Good immunity, endurance & metabolism"),
    HAIR("https://video2.topstretching-app.digitalfalcon.ae/assets/icons/hair.png", "Perfect condition of skin and hair"),
    STAR("https://video2.topstretching-app.digitalfalcon.ae/assets/icons/star.png", "Stable self-confidence & self-discipline");

    private final String icon;
    private final String description;

    IconDescription(String icon, String description) {
        this.icon = icon;
        this.description = description;
    }

    // Converts the enum into a List<IconObject>
    public static List<IconObject> toList() {
        return Stream.of(IconDescription.values())
                .map(IconDescription::getIconObject)
                .collect(Collectors.toList());
    }

    @JsonValue
    public IconObject getIconObject() {
        return new IconObject(icon, description);
    }

    public record IconObject(String icon, String description) {
        // Constructor for record class
        public IconObject(String icon, String description) {
            this.icon = icon;
            this.description = description;
        }
    }
}
