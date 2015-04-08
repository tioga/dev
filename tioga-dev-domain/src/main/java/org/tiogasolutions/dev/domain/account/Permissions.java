package org.tiogasolutions.dev.domain.account;

import java.io.Serializable;
import java.util.*;

public class Permissions implements Serializable {

  private Set<String> roleTypes = new TreeSet<>();

  public Permissions() {
  }

  public Permissions(Collection<String> roleTypes) {
    if (roleTypes != null) {
      this.roleTypes.addAll(roleTypes);
    }
  }

  public Set<String> getRoleTypes() {
    return Collections.unmodifiableSet(roleTypes);
  }

  public void setRoleTypes(Set<String> roleTypes) {
    this.roleTypes.clear();
    this.roleTypes.addAll(roleTypes);
  }

  public boolean contains(String roleType) {
    return roleTypes.contains(roleType);
  }

  public boolean addRoleType(String roleType) {
    return roleTypes.add(roleType);
  }

  public boolean removeRoleType(String roleType) {
    return roleTypes.remove(roleType);
  }
}
