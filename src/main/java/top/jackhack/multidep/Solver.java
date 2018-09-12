package top.jackhack.multidep;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Solver {
    private List<Item> items = new ArrayList<>();
    public static final int ROOT_ID = 0;
    private Multimap<Integer, State> map = ArrayListMultimap.create();

    public Solver() {
        items.add(new Item(0, 0));
    }

    public Item addItem(Item item) {
        item.id = items.size();
        items.add(item);
        addDep(item.id, 0);
        return item;
    }

    public void addDep(int i, int dep_id) {
        items.get(dep_id).chd |= 1 << i;
        items.get(i).dep |= 1 << dep_id;
    }


    public int solve(int m) {
        LinkedList<State> qu = new LinkedList<>();
        HashSet<Integer> in_queue = new HashSet<>();
        qu.push(new State(1<<ROOT_ID, 0, 0, ROOT_ID));
        while (!qu.isEmpty()) {
            State st = qu.getLast();
            qu.removeLast();
            System.out.println(st);
            int p = st.pos;
            int s = items.get(p).chd & ~st.added;

            while (s != 0) {
                final int lb = Integer.lowestOneBit(s);
                s ^= lb;

                final int index = Integer.numberOfTrailingZeros(lb);
                final Item child_item = items.get(index);
                final int new_added = st.added | lb;
                final int new_value = st.v + child_item.v;
                final int new_weight = st.w + child_item.w;
                if (new_weight > m) continue;
                State chlid_state = new State(new_added, new_value, new_weight, index);

                if (child_item.check(st.added)) {

                    if (!in_queue.contains(new_added))
                    {
                        map.put(new_weight, chlid_state);
                        in_queue.add(new_added);
                        qu.push(chlid_state);
                        qu.push(new State(chlid_state.added, chlid_state.v, new_weight, ROOT_ID));
                    }
                }
            }

        }
        final int[] max = {Integer.MIN_VALUE};
        map.forEach((k, v) -> {if(k <= m) max[0] = Integer.max(v.v, max[0]);});
        return max[0];
    }
}
