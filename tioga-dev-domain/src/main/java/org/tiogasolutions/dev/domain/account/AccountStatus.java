package org.tiogasolutions.dev.domain.account;

import java.io.Serializable;

public class AccountStatus implements Serializable {

  private boolean enabled;
  private boolean credentialsNonExpired;
  private boolean accountNonLocked;
  private boolean accountNonExpired;

  public AccountStatus(boolean enabled,
                       boolean credentialsNonExpired,
                       boolean accountNonLocked,
                       boolean accountNonExpired) {

    this.enabled = enabled;
    this.credentialsNonExpired = credentialsNonExpired;
    this.accountNonLocked = accountNonLocked;
    this.accountNonExpired = accountNonExpired;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }
}
