package domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String name;
    private String password;
    private String email;

    private Level level;
    private int login;
    private int recommend;

    public void upgradeLevel() {
        Level nextLevel = level.nextLevel();
        if(nextLevel == null) {
            throw new IllegalStateException(level + "은 업그레이드가 불가능합니다");
        }
        else {
            level = nextLevel;
        }
    }
}
