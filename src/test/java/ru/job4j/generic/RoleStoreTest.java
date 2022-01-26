package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class RoleStoreTest {

    @Test
    public void successfullyAddItemAndFindItem() {
        String roleId = "1";
        String roleName = "testRole1";
        Role role = new Role(roleId, roleName);
        RoleStore roleStore = new RoleStore();

        roleStore.add(role);

        assertThat(roleStore.findById("1"), equalTo(role));
    }

    @Test
    public void returnNullWhenItemIdIsMissingInStore() {
        String roleId = "1";
        String roleName = "testRole1";
        Role role = new Role(roleId, roleName);
        RoleStore roleStore = new RoleStore();

        roleStore.add(role);

        assertThat(roleStore.findById("12"), equalTo(null));
    }

    @Test
    public void successfullyKeepFirstItemWhenSecondItemHasDuplicateId() {
        String roleId1 = "1";
        String roleName1 = "testRole1";
        Role role1 = new Role(roleId1, roleName1);
        String roleId2 = "1";
        String roleName2 = "testRole2";
        Role role2 = new Role(roleId2, roleName2);
        RoleStore roleStore = new RoleStore();
        roleStore.add(role1);
        roleStore.add(role2);

        assertThat(roleStore.findById("1").getName(), equalTo("testRole1"));
    }

    @Test
    public void successfullyReplaceItem() {
        String roleId1 = "1";
        String roleName1 = "testRole1";
        Role role1 = new Role(roleId1, roleName1);
        String roleId2 = "2";
        String roleName2 = "testRole2";
        Role role2 = new Role(roleId2, roleName2);
        RoleStore roleStore = new RoleStore();


        roleStore.add(role1);
        roleStore.replace("1", role2);


        assertThat(roleStore.findById("1").getName(), equalTo(roleName2));
    }

    @Test
    public void doesNotReplaceItemWhenItemIdIsMissingInStore() {
        String roleId1 = "1";
        String roleName1 = "testRole1";
        Role role1 = new Role(roleId1, roleName1);
        String roleId2 = "2";
        String roleName2 = "testRole2";
        Role role2 = new Role(roleId2, roleName2);
        RoleStore roleStore = new RoleStore();


        roleStore.add(role1);
        roleStore.replace("15", role2);


        assertThat(roleStore.findById("1").getName(), equalTo(roleName1));
    }

    @Test
    public void successfullyDeleteAnItem() {
        String roleId1 = "1";
        String roleName1 = "testRole1";
        Role role1 = new Role(roleId1, roleName1);
        String roleId2 = "2";
        String roleName2 = "testRole2";
        Role role2 = new Role(roleId2, roleName2);
        RoleStore roleStore = new RoleStore();


        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.delete("1");


        assertThat(roleStore.findById("2").getName(), equalTo(roleName2));
        assertThat(roleStore.findById("1"), equalTo(null));
    }

    @Test
    public void doesNotDeleteAnItemsWhenItemIdIsMissingInStore() {
        String roleId1 = "1";
        String roleName1 = "testRole1";
        Role role1 = new Role(roleId1, roleName1);
        String roleId2 = "2";
        String roleName2 = "testRole2";
        Role role2 = new Role(roleId2, roleName2);
        RoleStore roleStore = new RoleStore();


        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.delete("13");


        assertThat(roleStore.findById("1").getName(), equalTo(roleName1));
        assertThat(roleStore.findById("2").getName(), equalTo(roleName2));
    }

}
