package dao;

import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;

import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkState;
import static generated.Tables.RECEIPTS;
import static generated.Tables.TAGS;

public class TagDao {
    DSLContext dsl;

    public TagDao(Configuration jooqConfig) {
        this.dsl = DSL.using(jooqConfig);
    }

    public void toggle(String tag, int id) {
        if (dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.eq(id)).fetchOne() != null) {
            if (dsl.selectFrom(TAGS).where(TAGS.ID.eq(id).and(TAGS.TAG.eq(tag))).fetchOne() == null) {
                // insert
                dsl.insertInto(TAGS).values(id, tag).execute();
            }
            else {
                // delete
                dsl.deleteFrom(TAGS).where(TAGS.ID.eq(id).and(TAGS.TAG.eq(tag))).execute();
            }
        }
    }

    public List<TagsRecord> getTags() {
        return dsl.selectFrom(TAGS).fetch();
    }

    public List<ReceiptsRecord> getReceiptsFromTag(String tag) {
        return dsl.selectFrom(RECEIPTS).where(RECEIPTS.ID.in(dsl.select(TAGS.ID).from(TAGS).where(TAGS.TAG.eq(tag)).fetch())).fetch();
    }
}
