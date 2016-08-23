/*
 * Copyright © 2016 <code@io7m.com> http://io7m.com
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

import com.io7m.jequality.annotations.EqualityStructural;
import com.io7m.jnull.NullCheck;
import com.io7m.jnull.Nullable;

/**
 * <p>
 * A computation that has failed.
 * </p>
 *
 * @param <F> The type of values returned upon failure.
 * @param <S> The type of values returned upon success.
 *
 * @see TryType
 */

@EqualityStructural
public final class Failure<F, S> implements TryType<F, S>
{
  private static final long serialVersionUID = -4218541696685737451L;
  private final F x;

  private Failure(
    final F in_x)
  {
    this.x = NullCheck.notNull(in_x, "Failure value");
  }

  /**
   * Produce a new failure value.
   *
   * @param x   The actual value
   * @param <F> The type of values returned upon failure.
   * @param <S> The type of values returned upon success.
   *
   * @return A new failure value
   *
   * @since 1.3.0
   */

  public static <F, S> Failure<F, S> failure(
    final F x)
  {
    return new Failure<F, S>(x);
  }

  @Override
  public <U> U accept(
    final TryVisitorType<F, S, U> v)
  {
    NullCheck.notNull(v, "Visitor");
    return v.failure(this);
  }

  @Override
  public <U, E extends Throwable> U acceptPartial(
    final TryPartialVisitorType<F, S, U, E> v)
    throws E
  {
    NullCheck.notNull(v, "Visitor");
    return v.failure(this);
  }

  @Override
  public boolean equals(
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
    final Failure<?, ?> other = (Failure<?, ?>) obj;
    return this.x.equals(other.x);
  }

  /**
   * @return The value contained within.
   */

  public F get()
  {
    return this.x;
  }

  @Override
  public int hashCode()
  {
    return this.x.hashCode();
  }

  @Override
  public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("[Failure ");
    builder.append(this.x);
    builder.append("]");
    final String s = builder.toString();
    assert s != null;
    return s;
  }
}
