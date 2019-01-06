package net.jayde.util.file.markdown;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class MarkdownMenu {
    int level;
    String id = UUID.randomUUID().toString();
    String name = "";
    String preId = "";
    List<MarkdownMenu> sonMenus = new ArrayList<>();
    String text = "";

    @Override
    public String toString() {
        return "MarkdownMenu{" +
                "level=" + level +
                ", name='" + name + '\'' +
                '}';
    }
}
