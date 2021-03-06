package com.robertx22.age_of_exile.saveclasses.spells.skill_gems;

import com.robertx22.age_of_exile.database.data.skill_gem.SkillGemData;
import com.robertx22.age_of_exile.database.data.skill_gem.SkillGemType;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.ArrayList;
import java.util.List;

@Storable
public class SkillGemsData implements Inventory {

    public static int SIZE = 20;

    @Store
    public List<ItemStack> stacks = new ArrayList<>(DefaultedList.ofSize(SIZE, ItemStack.EMPTY));

    public enum Places {

        B1(0, 0, EquipmentSlot.HEAD, SkillGemType.SUPPORT_GEM, 57, 9),
        B2(1, 0, EquipmentSlot.HEAD, SkillGemType.SKILL_GEM, 79, 9),
        B3(2, 0, EquipmentSlot.HEAD, SkillGemType.SUPPORT_GEM, 101, 9),

        B4(3, 1, EquipmentSlot.CHEST, SkillGemType.SUPPORT_GEM, 39, 32),
        B5(4, 1, EquipmentSlot.CHEST, SkillGemType.SUPPORT_GEM, 57, 32),
        B6(5, 1, EquipmentSlot.CHEST, SkillGemType.SKILL_GEM, 79, 32),
        B7(6, 1, EquipmentSlot.CHEST, SkillGemType.SUPPORT_GEM, 101, 32),
        B8(7, 1, EquipmentSlot.CHEST, SkillGemType.SUPPORT_GEM, 119, 32),

        B9(8, 2, EquipmentSlot.LEGS, SkillGemType.SUPPORT_GEM, 39, 55),
        B10(9, 2, EquipmentSlot.LEGS, SkillGemType.SUPPORT_GEM, 57, 55),
        B11(10, 2, EquipmentSlot.LEGS, SkillGemType.SKILL_GEM, 79, 55),
        B12(11, 2, EquipmentSlot.LEGS, SkillGemType.SUPPORT_GEM, 101, 55),
        B13(12, 2, EquipmentSlot.LEGS, SkillGemType.SUPPORT_GEM, 119, 55),

        B14(13, 3, EquipmentSlot.FEET, SkillGemType.SUPPORT_GEM, 57, 78),
        B15(14, 3, EquipmentSlot.FEET, SkillGemType.SKILL_GEM, 79, 78),
        B16(15, 3, EquipmentSlot.FEET, SkillGemType.SUPPORT_GEM, 101, 78),

        B17(16, 4, null, SkillGemType.SKILL_GEM, 43, 101),
        B18(17, 5, null, SkillGemType.SKILL_GEM, 67, 101),
        B19(18, 6, null, SkillGemType.SKILL_GEM, 91, 101),
        B20(19, 7, null, SkillGemType.SKILL_GEM, 115, 101);

        public int index;
        public int place;
        public EquipmentSlot equipSlot;
        public SkillGemType slotType;

        public int x;
        public int y;

        Places(int index, int place, EquipmentSlot equipSlot, SkillGemType slotType, int x, int y) {
            this.index = index;
            this.place = place;
            this.equipSlot = equipSlot;
            this.slotType = slotType;
            this.x = x;
            this.y = y;
        }
    }

    public List<SkillGemData> getSupportGemsOf(int place) {
        List<ItemStack> list = new ArrayList<>();

        for (Places en : Places.values()) {
            if (en.slotType == SkillGemType.SUPPORT_GEM) {
                if (en.place == place) {
                    list.add(stacks.get(en.index));
                }
            }
        }
        List<SkillGemData> gems = new ArrayList<>();

        list.forEach(x -> {
            SkillGemData d = SkillGemData.fromStack(x);
            if (d != null) {
                gems.add(d);
            }
        });

        return gems;
    }

    public SkillGemData getSkillGemOf(int place) {
        for (Places en : Places.values()) {
            if (en.place == place) {
                if (en.slotType == SkillGemType.SKILL_GEM) {
                    SkillGemData gem = SkillGemData.fromStack(stacks.get(en.index));
                    return gem;
                }
            }
        }
        System.out.print("No skill gem found for place " + place);
        return null;
    }

    @Override

    public int size() {
        return SIZE;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getStack(int slot) {
        return stacks.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack itemStack = Inventories.splitStack(this.stacks, slot, amount);
        return itemStack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        return stacks.set(slot, ItemStack.EMPTY);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        stacks.set(slot, stack);
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.stacks.clear();
    }
}
