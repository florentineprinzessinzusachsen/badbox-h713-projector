package e0;

import androidx.work.impl.WorkDatabase_Impl;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.ListIterator;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class w extends p.v {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ WorkDatabase_Impl f688d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public w(WorkDatabase_Impl workDatabase_Impl) {
        super(24, "08b926448d86528e697981ddd30459f7", "149fd8ad55885d3fe3549a37a0163243");
        this.f688d = workDatabase_Impl;
    }

    @Override // p.v
    public final void a(w.a aVar) {
        j2.i.e(aVar, "connection");
        l3.h.y(aVar, "CREATE TABLE IF NOT EXISTS `Dependency` (`work_spec_id` TEXT NOT NULL, `prerequisite_id` TEXT NOT NULL, PRIMARY KEY(`work_spec_id`, `prerequisite_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE , FOREIGN KEY(`prerequisite_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        l3.h.y(aVar, "CREATE INDEX IF NOT EXISTS `index_Dependency_work_spec_id` ON `Dependency` (`work_spec_id`)");
        l3.h.y(aVar, "CREATE INDEX IF NOT EXISTS `index_Dependency_prerequisite_id` ON `Dependency` (`prerequisite_id`)");
        l3.h.y(aVar, "CREATE TABLE IF NOT EXISTS `WorkSpec` (`id` TEXT NOT NULL, `state` INTEGER NOT NULL, `worker_class_name` TEXT NOT NULL, `input_merger_class_name` TEXT NOT NULL, `input` BLOB NOT NULL, `output` BLOB NOT NULL, `initial_delay` INTEGER NOT NULL, `interval_duration` INTEGER NOT NULL, `flex_duration` INTEGER NOT NULL, `run_attempt_count` INTEGER NOT NULL, `backoff_policy` INTEGER NOT NULL, `backoff_delay_duration` INTEGER NOT NULL, `last_enqueue_time` INTEGER NOT NULL DEFAULT -1, `minimum_retention_duration` INTEGER NOT NULL, `schedule_requested_at` INTEGER NOT NULL, `run_in_foreground` INTEGER NOT NULL, `out_of_quota_policy` INTEGER NOT NULL, `period_count` INTEGER NOT NULL DEFAULT 0, `generation` INTEGER NOT NULL DEFAULT 0, `next_schedule_time_override` INTEGER NOT NULL DEFAULT 9223372036854775807, `next_schedule_time_override_generation` INTEGER NOT NULL DEFAULT 0, `stop_reason` INTEGER NOT NULL DEFAULT -256, `trace_tag` TEXT, `backoff_on_system_interruptions` INTEGER, `required_network_type` INTEGER NOT NULL, `required_network_request` BLOB NOT NULL DEFAULT x'', `requires_charging` INTEGER NOT NULL, `requires_device_idle` INTEGER NOT NULL, `requires_battery_not_low` INTEGER NOT NULL, `requires_storage_not_low` INTEGER NOT NULL, `trigger_content_update_delay` INTEGER NOT NULL, `trigger_max_content_delay` INTEGER NOT NULL, `content_uri_triggers` BLOB NOT NULL, PRIMARY KEY(`id`))");
        l3.h.y(aVar, "CREATE INDEX IF NOT EXISTS `index_WorkSpec_schedule_requested_at` ON `WorkSpec` (`schedule_requested_at`)");
        l3.h.y(aVar, "CREATE INDEX IF NOT EXISTS `index_WorkSpec_last_enqueue_time` ON `WorkSpec` (`last_enqueue_time`)");
        l3.h.y(aVar, "CREATE TABLE IF NOT EXISTS `WorkTag` (`tag` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`tag`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        l3.h.y(aVar, "CREATE INDEX IF NOT EXISTS `index_WorkTag_work_spec_id` ON `WorkTag` (`work_spec_id`)");
        l3.h.y(aVar, "CREATE TABLE IF NOT EXISTS `SystemIdInfo` (`work_spec_id` TEXT NOT NULL, `generation` INTEGER NOT NULL DEFAULT 0, `system_id` INTEGER NOT NULL, PRIMARY KEY(`work_spec_id`, `generation`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        l3.h.y(aVar, "CREATE TABLE IF NOT EXISTS `WorkName` (`name` TEXT NOT NULL, `work_spec_id` TEXT NOT NULL, PRIMARY KEY(`name`, `work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        l3.h.y(aVar, "CREATE INDEX IF NOT EXISTS `index_WorkName_work_spec_id` ON `WorkName` (`work_spec_id`)");
        l3.h.y(aVar, "CREATE TABLE IF NOT EXISTS `WorkProgress` (`work_spec_id` TEXT NOT NULL, `progress` BLOB NOT NULL, PRIMARY KEY(`work_spec_id`), FOREIGN KEY(`work_spec_id`) REFERENCES `WorkSpec`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )");
        l3.h.y(aVar, "CREATE TABLE IF NOT EXISTS `Preference` (`key` TEXT NOT NULL, `long_value` INTEGER, PRIMARY KEY(`key`))");
        l3.h.y(aVar, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        l3.h.y(aVar, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '08b926448d86528e697981ddd30459f7')");
    }

    @Override // p.v
    public final void b(w.a aVar) {
        j2.i.e(aVar, "connection");
        l3.h.y(aVar, "DROP TABLE IF EXISTS `Dependency`");
        l3.h.y(aVar, "DROP TABLE IF EXISTS `WorkSpec`");
        l3.h.y(aVar, "DROP TABLE IF EXISTS `WorkTag`");
        l3.h.y(aVar, "DROP TABLE IF EXISTS `SystemIdInfo`");
        l3.h.y(aVar, "DROP TABLE IF EXISTS `WorkName`");
        l3.h.y(aVar, "DROP TABLE IF EXISTS `WorkProgress`");
        l3.h.y(aVar, "DROP TABLE IF EXISTS `Preference`");
    }

    @Override // p.v
    public final void c(w.a aVar) {
        j2.i.e(aVar, "connection");
    }

    @Override // p.v
    public final void d(w.a aVar) {
        j2.i.e(aVar, "connection");
        l3.h.y(aVar, "PRAGMA foreign_keys = ON");
        p.h hVarF = this.f688d.f();
        p.i0 i0Var = hVarF.f1646b;
        i0Var.getClass();
        w.c cVarP = aVar.P("PRAGMA query_only");
        try {
            cVarP.F();
            boolean zV = cVarP.v();
            l3.h.k(cVarP, null);
            if (!zV) {
                l3.h.y(aVar, "PRAGMA temp_store = MEMORY");
                l3.h.y(aVar, "PRAGMA recursive_triggers = 1");
                l3.h.y(aVar, "DROP TABLE IF EXISTS room_table_modification_log");
                if (i0Var.f1667d) {
                    l3.h.y(aVar, "CREATE TEMP TABLE IF NOT EXISTS room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
                } else {
                    l3.h.y(aVar, p2.p.x0("CREATE TEMP TABLE IF NOT EXISTS room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)", "TEMP", ""));
                }
                e3.h hVar = i0Var.f1671h;
                ReentrantLock reentrantLock = (ReentrantLock) hVar.f750e;
                reentrantLock.lock();
                try {
                    hVar.f749d = true;
                    reentrantLock.unlock();
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            }
            synchronized (hVarF.f1651g) {
            }
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                l3.h.k(cVarP, th2);
                throw th3;
            }
        }
    }

    @Override // p.v
    public final void e(w.a aVar) {
        j2.i.e(aVar, "connection");
    }

    @Override // p.v
    public final void f(w.a aVar) {
        j2.i.e(aVar, "connection");
        w1.c cVar = new w1.c(10);
        w.c cVarP = aVar.P("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        while (cVarP.F()) {
            try {
                cVar.add(cVarP.n(0));
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    l3.h.k(cVarP, th);
                    throw th2;
                }
            }
        }
        l3.h.k(cVarP, null);
        ListIterator listIterator = l3.h.d(cVar).listIterator(0);
        while (true) {
            w1.a aVar2 = (w1.a) listIterator;
            if (!aVar2.hasNext()) {
                return;
            }
            String str = (String) aVar2.next();
            if (p2.p.z0(str, "room_fts_content_sync_", false)) {
                l3.h.y(aVar, "DROP TRIGGER IF EXISTS ".concat(str));
            }
        }
    }

    @Override // p.v
    public final p.u g(w.a aVar) {
        j2.i.e(aVar, "connection");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("work_spec_id", new v.h("work_spec_id", "TEXT", true, 1, null, 1));
        linkedHashMap.put("prerequisite_id", new v.h("prerequisite_id", "TEXT", true, 2, null, 1));
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new v.i("WorkSpec", "CASCADE", "CASCADE", l3.h.S("work_spec_id"), l3.h.S("id")));
        linkedHashSet.add(new v.i("WorkSpec", "CASCADE", "CASCADE", l3.h.S("prerequisite_id"), l3.h.S("id")));
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        linkedHashSet2.add(new v.j("index_Dependency_work_spec_id", false, l3.h.S("work_spec_id"), l3.h.S("ASC")));
        linkedHashSet2.add(new v.j("index_Dependency_prerequisite_id", false, l3.h.S("prerequisite_id"), l3.h.S("ASC")));
        v.k kVar = new v.k("Dependency", linkedHashMap, linkedHashSet, linkedHashSet2);
        v.k kVarH = d0.l0.H(aVar, "Dependency");
        if (!kVar.equals(kVarH)) {
            return new p.u("Dependency(androidx.work.impl.model.Dependency).\n Expected:\n" + kVar + "\n Found:\n" + kVarH, false);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        linkedHashMap2.put("id", new v.h("id", "TEXT", true, 1, null, 1));
        linkedHashMap2.put("state", new v.h("state", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("worker_class_name", new v.h("worker_class_name", "TEXT", true, 0, null, 1));
        linkedHashMap2.put("input_merger_class_name", new v.h("input_merger_class_name", "TEXT", true, 0, null, 1));
        linkedHashMap2.put("input", new v.h("input", "BLOB", true, 0, null, 1));
        linkedHashMap2.put("output", new v.h("output", "BLOB", true, 0, null, 1));
        linkedHashMap2.put("initial_delay", new v.h("initial_delay", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("interval_duration", new v.h("interval_duration", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("flex_duration", new v.h("flex_duration", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("run_attempt_count", new v.h("run_attempt_count", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("backoff_policy", new v.h("backoff_policy", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("backoff_delay_duration", new v.h("backoff_delay_duration", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("last_enqueue_time", new v.h("last_enqueue_time", "INTEGER", true, 0, "-1", 1));
        linkedHashMap2.put("minimum_retention_duration", new v.h("minimum_retention_duration", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("schedule_requested_at", new v.h("schedule_requested_at", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("run_in_foreground", new v.h("run_in_foreground", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("out_of_quota_policy", new v.h("out_of_quota_policy", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("period_count", new v.h("period_count", "INTEGER", true, 0, "0", 1));
        linkedHashMap2.put("generation", new v.h("generation", "INTEGER", true, 0, "0", 1));
        linkedHashMap2.put("next_schedule_time_override", new v.h("next_schedule_time_override", "INTEGER", true, 0, "9223372036854775807", 1));
        linkedHashMap2.put("next_schedule_time_override_generation", new v.h("next_schedule_time_override_generation", "INTEGER", true, 0, "0", 1));
        linkedHashMap2.put("stop_reason", new v.h("stop_reason", "INTEGER", true, 0, "-256", 1));
        linkedHashMap2.put("trace_tag", new v.h("trace_tag", "TEXT", false, 0, null, 1));
        linkedHashMap2.put("backoff_on_system_interruptions", new v.h("backoff_on_system_interruptions", "INTEGER", false, 0, null, 1));
        linkedHashMap2.put("required_network_type", new v.h("required_network_type", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("required_network_request", new v.h("required_network_request", "BLOB", true, 0, "x''", 1));
        linkedHashMap2.put("requires_charging", new v.h("requires_charging", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("requires_device_idle", new v.h("requires_device_idle", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("requires_battery_not_low", new v.h("requires_battery_not_low", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("requires_storage_not_low", new v.h("requires_storage_not_low", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("trigger_content_update_delay", new v.h("trigger_content_update_delay", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("trigger_max_content_delay", new v.h("trigger_max_content_delay", "INTEGER", true, 0, null, 1));
        linkedHashMap2.put("content_uri_triggers", new v.h("content_uri_triggers", "BLOB", true, 0, null, 1));
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        LinkedHashSet linkedHashSet4 = new LinkedHashSet();
        linkedHashSet4.add(new v.j("index_WorkSpec_schedule_requested_at", false, l3.h.S("schedule_requested_at"), l3.h.S("ASC")));
        linkedHashSet4.add(new v.j("index_WorkSpec_last_enqueue_time", false, l3.h.S("last_enqueue_time"), l3.h.S("ASC")));
        v.k kVar2 = new v.k("WorkSpec", linkedHashMap2, linkedHashSet3, linkedHashSet4);
        v.k kVarH2 = d0.l0.H(aVar, "WorkSpec");
        if (!kVar2.equals(kVarH2)) {
            return new p.u("WorkSpec(androidx.work.impl.model.WorkSpec).\n Expected:\n" + kVar2 + "\n Found:\n" + kVarH2, false);
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put("tag", new v.h("tag", "TEXT", true, 1, null, 1));
        linkedHashMap3.put("work_spec_id", new v.h("work_spec_id", "TEXT", true, 2, null, 1));
        LinkedHashSet linkedHashSet5 = new LinkedHashSet();
        linkedHashSet5.add(new v.i("WorkSpec", "CASCADE", "CASCADE", l3.h.S("work_spec_id"), l3.h.S("id")));
        LinkedHashSet linkedHashSet6 = new LinkedHashSet();
        linkedHashSet6.add(new v.j("index_WorkTag_work_spec_id", false, l3.h.S("work_spec_id"), l3.h.S("ASC")));
        v.k kVar3 = new v.k("WorkTag", linkedHashMap3, linkedHashSet5, linkedHashSet6);
        v.k kVarH3 = d0.l0.H(aVar, "WorkTag");
        if (!kVar3.equals(kVarH3)) {
            return new p.u("WorkTag(androidx.work.impl.model.WorkTag).\n Expected:\n" + kVar3 + "\n Found:\n" + kVarH3, false);
        }
        LinkedHashMap linkedHashMap4 = new LinkedHashMap();
        linkedHashMap4.put("work_spec_id", new v.h("work_spec_id", "TEXT", true, 1, null, 1));
        linkedHashMap4.put("generation", new v.h("generation", "INTEGER", true, 2, "0", 1));
        linkedHashMap4.put("system_id", new v.h("system_id", "INTEGER", true, 0, null, 1));
        LinkedHashSet linkedHashSet7 = new LinkedHashSet();
        linkedHashSet7.add(new v.i("WorkSpec", "CASCADE", "CASCADE", l3.h.S("work_spec_id"), l3.h.S("id")));
        v.k kVar4 = new v.k("SystemIdInfo", linkedHashMap4, linkedHashSet7, new LinkedHashSet());
        v.k kVarH4 = d0.l0.H(aVar, "SystemIdInfo");
        if (!kVar4.equals(kVarH4)) {
            return new p.u("SystemIdInfo(androidx.work.impl.model.SystemIdInfo).\n Expected:\n" + kVar4 + "\n Found:\n" + kVarH4, false);
        }
        LinkedHashMap linkedHashMap5 = new LinkedHashMap();
        linkedHashMap5.put("name", new v.h("name", "TEXT", true, 1, null, 1));
        linkedHashMap5.put("work_spec_id", new v.h("work_spec_id", "TEXT", true, 2, null, 1));
        LinkedHashSet linkedHashSet8 = new LinkedHashSet();
        linkedHashSet8.add(new v.i("WorkSpec", "CASCADE", "CASCADE", l3.h.S("work_spec_id"), l3.h.S("id")));
        LinkedHashSet linkedHashSet9 = new LinkedHashSet();
        linkedHashSet9.add(new v.j("index_WorkName_work_spec_id", false, l3.h.S("work_spec_id"), l3.h.S("ASC")));
        v.k kVar5 = new v.k("WorkName", linkedHashMap5, linkedHashSet8, linkedHashSet9);
        v.k kVarH5 = d0.l0.H(aVar, "WorkName");
        if (!kVar5.equals(kVarH5)) {
            return new p.u("WorkName(androidx.work.impl.model.WorkName).\n Expected:\n" + kVar5 + "\n Found:\n" + kVarH5, false);
        }
        LinkedHashMap linkedHashMap6 = new LinkedHashMap();
        linkedHashMap6.put("work_spec_id", new v.h("work_spec_id", "TEXT", true, 1, null, 1));
        linkedHashMap6.put("progress", new v.h("progress", "BLOB", true, 0, null, 1));
        LinkedHashSet linkedHashSet10 = new LinkedHashSet();
        linkedHashSet10.add(new v.i("WorkSpec", "CASCADE", "CASCADE", l3.h.S("work_spec_id"), l3.h.S("id")));
        v.k kVar6 = new v.k("WorkProgress", linkedHashMap6, linkedHashSet10, new LinkedHashSet());
        v.k kVarH6 = d0.l0.H(aVar, "WorkProgress");
        if (!kVar6.equals(kVarH6)) {
            return new p.u("WorkProgress(androidx.work.impl.model.WorkProgress).\n Expected:\n" + kVar6 + "\n Found:\n" + kVarH6, false);
        }
        LinkedHashMap linkedHashMap7 = new LinkedHashMap();
        linkedHashMap7.put("key", new v.h("key", "TEXT", true, 1, null, 1));
        linkedHashMap7.put("long_value", new v.h("long_value", "INTEGER", false, 0, null, 1));
        v.k kVar7 = new v.k("Preference", linkedHashMap7, new LinkedHashSet(), new LinkedHashSet());
        v.k kVarH7 = d0.l0.H(aVar, "Preference");
        if (kVar7.equals(kVarH7)) {
            return new p.u(null, true);
        }
        return new p.u("Preference(androidx.work.impl.model.Preference).\n Expected:\n" + kVar7 + "\n Found:\n" + kVarH7, false);
    }
}
