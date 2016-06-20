package flexbox.tag;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.flexbox.FlexboxLayout;

import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerFlow extends FlexboxLayout {
    private Adapter mAdapter;

    public RecyclerFlow(Context context) {
        super(context);
        init();
    }

    public RecyclerFlow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RecyclerFlow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setFlexDirection(FlexboxLayout.FLEX_DIRECTION_ROW);
        setFlexWrap(FLEX_WRAP_WRAP);

    }

    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;

        if (mAdapter == null) {
            removeAllViews();
            return;
        }

        mAdapter.addOnChanged(new OnChanged() {
            @Override public void onChanged() {
                onChanged();
            }
        });
        onChanged();
    }

    private void onChanged() {
        removeAllViews();
        for (int i = 0, n = mAdapter.getCount(); i < n; i++) {
            addView(mAdapter.getView(this, i, mAdapter.getItem(i)));
        }
    }

    public static abstract class Adapter<T> {
        private List<T> list;
        //private List<ViewHolder> viewholders; // TODO
        private Set<OnChanged> onChanges;

        public Adapter() {
            this((List) null);
        }

        public Adapter(List<T> list) {
            //if (list == null) list = Collections.emptyList();
            if (list == null) list = new ArrayList<>(); // need mutable
            this.list = list;
        }

        public Adapter(T[] array) {
            this.list = Arrays.asList(list);
        }

        protected abstract View getView(ViewGroup parent, int position, T t);

        protected T getItem(int position) {
            return list.get(position);
        }

        protected int getCount() {
            return list.size();
        }

        public void notifyDataSetChanged() {
            for (OnChanged onChanged : getOnChanges())

            if (onChanged != null) {
                onChanged.onChanged();
            }
        }

        protected Set<OnChanged> getOnChanges() {
            if (onChanges == null) onChanges = Collections.synchronizedSet(new HashSet<OnChanged>());

            return onChanges;
        }

        public void addOnChanged(OnChanged onChanged) {
            getOnChanges().add(onChanged);
        }

        public interface OnChanged {
            void onChanged();
        }

        public static <R> Adpater<R> create() {
            return new Adpater<>();
        }
    }
}
