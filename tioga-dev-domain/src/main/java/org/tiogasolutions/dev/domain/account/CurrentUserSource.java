package org.tiogasolutions.dev.domain.account;

public interface CurrentUserSource {

  String getAccountId();

  String getUsername();
  String getPassword();

  Permissions getPermissions();
  AccountStatus getAccountStatus();
}
