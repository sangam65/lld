package splitwise.entites;

import java.util.List;
import java.util.UUID;

public class Group {
    private final List<User> members;
    private final String groupId;
    private final String name;
    public List<User> getMembers() {
        return members;
    }
    public String getGroupId() {
        return groupId;
    }
    public String getName() {
        return name;
    }
    public Group(List<User> members, String name) {
        this.members = members;
        this.groupId = UUID.randomUUID().toString();
        this.name = name;
    }
}
