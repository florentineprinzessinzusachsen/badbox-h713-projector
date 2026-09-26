package android.support.v17.leanback.util;

import android.support.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class StateMachine {
    public static final int STATUS_EXECUTED = 2;
    public static final int STATUS_INVOKED = 1;
    public static final int STATUS_ZERO = 0;
    private boolean mSorted = true;
    private final ArrayList<State> mSortedList = new ArrayList<>();

    public static class State {
        ArrayList<State> mPriorStates;
        private int mStatus;

        public boolean canRun() {
            return true;
        }

        public final boolean equals(Object obj) {
            return this == obj;
        }

        public void run() {
        }

        final boolean runIfNeeded() {
            if (this.mStatus != 2) {
                if (this.mStatus != 1 || !canRun()) {
                    return false;
                }
                run();
                this.mStatus = 2;
            }
            return true;
        }

        void addPriorState(State state) {
            if (this.mPriorStates == null) {
                this.mPriorStates = new ArrayList<>();
            }
            if (this.mPriorStates.contains(state)) {
                return;
            }
            this.mPriorStates.add(state);
        }

        final void markInvoked() {
            if (this.mStatus == 0) {
                this.mStatus = 1;
            }
        }

        final void updateStatus(int i) {
            this.mStatus = i;
        }

        public final int getStatus() {
            return this.mStatus;
        }
    }

    public void addState(State state) {
        if (this.mSortedList.contains(state)) {
            return;
        }
        state.updateStatus(0);
        this.mSortedList.add(state);
    }

    public void addState(State state, State state2) {
        addState(state);
        addState(state2);
        state2.addPriorState(state);
        this.mSorted = false;
    }

    void verifySorted() {
        if (!this.mSorted) {
            throw new RuntimeException("Graph not sorted");
        }
    }

    public void runState(State state) {
        verifySorted();
        state.markInvoked();
        runPendingStates();
    }

    public void runPendingStates() {
        verifySorted();
        int size = this.mSortedList.size();
        for (int i = 0; i < size && this.mSortedList.get(i).runIfNeeded(); i++) {
        }
    }

    public void resetStatus() {
        int size = this.mSortedList.size();
        for (int i = 0; i < size; i++) {
            this.mSortedList.get(i).updateStatus(0);
        }
    }

    public void sort() {
        if (this.mSorted) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        HashMap map = new HashMap();
        for (int size = this.mSortedList.size() - 1; size >= 0; size--) {
            State state = this.mSortedList.get(size);
            if (state.mPriorStates != null && state.mPriorStates.size() > 0) {
                map.put(state, new ArrayList(state.mPriorStates));
            } else {
                arrayList2.add(state);
            }
        }
        while (!arrayList2.isEmpty()) {
            State state2 = (State) arrayList2.remove(arrayList2.size() - 1);
            arrayList.add(state2);
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                ArrayList arrayList3 = (ArrayList) entry.getValue();
                if (arrayList3.remove(state2) && arrayList3.size() == 0) {
                    arrayList2.add((State) entry.getKey());
                    it.remove();
                }
            }
        }
        if (map.size() > 0) {
            throw new RuntimeException("Cycle in Graph");
        }
        this.mSortedList.clear();
        this.mSortedList.addAll(arrayList);
        this.mSorted = true;
    }
}
