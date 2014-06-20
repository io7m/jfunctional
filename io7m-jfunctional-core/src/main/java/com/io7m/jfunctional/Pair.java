/*
 * Copyright © 2014 <code@io7m.com> http://io7m.com
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jfunctional;

import java.io.Serializable;

import com.io7m.jequality.annotations.EqualityStructural;
import com.io7m.jnull.NullCheck;
import com.io7m.jnull.Nullable;

/**
 * Immutable generic pair type.
 * 
 * @param <A>
 *          The type of left values.
 * @param <B>
 *          The type of right values.
 */

@EqualityStructural public final class Pair<A, B> implements Serializable
{
  private static final long serialVersionUID = 5737112071204239831L;

  /**
   * Return a new pair <code>(left, right)</code>.
   * 
   * @param <A>
   *          The type of left values.
   * @param <B>
   *          The type of right values.
   * @param left
   *          The left value
   * @param right
   *          The right value
   * @return A new pair
   */

  public static <A, B> Pair<A, B> pair(
    final A left,
    final B right)
  {
    return new Pair<A, B>(left, right);
  }

  private final A left;
  private final B right;

  private Pair(
    final A in_left,
    final B in_right)
  {
    this.left = NullCheck.notNull(in_left, "Left");
    this.right = NullCheck.notNull(in_right, "Right");
  }

  @Override public boolean equals(
    final @Nullable Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    final Pair<?, ?> other = (Pair<?, ?>) obj;
    return this.left.equals(other.left) && this.right.equals(other.right);
  }

  /**
   * @return The left value of the pair.
   */

  public A getLeft()
  {
    return this.left;
  }

  /**
   * @return The right value of the pair.
   */

  public B getRight()
  {
    return this.right;
  }

  @Override public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + this.left.hashCode();
    result = (prime * result) + this.right.hashCode();
    return result;
  }

  @Override public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("[Pair ");
    builder.append(this.left);
    builder.append(" ");
    builder.append(this.right);
    builder.append("]");
    final String s = builder.toString();
    assert s != null;
    return s;
  }
}
