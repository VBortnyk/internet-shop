package mate.academy.internetshop.db;

import java.util.ArrayList;
import java.util.List;
import mate.academy.internetshop.model.Item;

public class Storage {
    public static Long idGenerator = 0L;
    public static final List<Item> items = new ArrayList<>();
}
