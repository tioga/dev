package org.tiogasolutions.dev.common.compare;

import java.time.LocalDateTime;

public class Note {

  private final String message;
  private final LocalDateTime createdAt;

  public Note(String createdAt, String message) {
    this.message = message;
    this.createdAt = LocalDateTime.parse(createdAt);
  }

  public String getMessage() {
    return message;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  @Override
  public String toString() {
    return "Note{" +
        "message='" + message + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Note note = (Note) o;

    if (createdAt != null ? !createdAt.equals(note.createdAt) : note.createdAt != null) return false;
    if (message != null ? !message.equals(note.message) : note.message != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = message != null ? message.hashCode() : 0;
    result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
    return result;
  }
}
