package com.robertx22.age_of_exile.database.data.stats.datapacks.serializers;

import com.google.gson.JsonObject;
import com.robertx22.age_of_exile.database.data.stats.datapacks.base.IStatSerializer;
import com.robertx22.age_of_exile.database.data.stats.datapacks.stats.SpecificSpellExtraProjectilesStat;

public class ExtraProjectileSer implements IStatSerializer<SpecificSpellExtraProjectilesStat> {

    @Override
    public JsonObject statToJson(SpecificSpellExtraProjectilesStat obj) {
        JsonObject json = new JsonObject();
        this.saveBaseStatValues(obj, json);
        json.addProperty("spell", obj.spell);
        return json;
    }

    @Override
    public SpecificSpellExtraProjectilesStat getStatFromJson(JsonObject json) {
        String spell = json.get("spell")
            .getAsString();
        SpecificSpellExtraProjectilesStat stat = new SpecificSpellExtraProjectilesStat(spell);
        this.loadBaseStatValues(stat, json);
        return stat;
    }
}
