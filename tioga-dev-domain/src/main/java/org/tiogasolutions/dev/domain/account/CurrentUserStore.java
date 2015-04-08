package org.tiogasolutions.dev.domain.account;

public interface CurrentUserStore {

  CurrentUserSource getCurrentUserSourceByName(String username);
  CurrentUserSource getCurrentUserSourceByEmail(String email);
}
