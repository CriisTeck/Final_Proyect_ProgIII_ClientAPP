package models;

public enum TypeAccount {
    MEMBER("MEMBER"), ADMIN("ADMIN");

    String name;

    TypeAccount(String str) {
        name = str;
    }

    String getName() {
        return name;
    }
}
