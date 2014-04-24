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

import com.io7m.jequality.annotations.EqualityStructural;
import com.io7m.jnull.NullCheck;
import com.io7m.jnull.Nullable;

/**
 * <p>
 * A value.
 * </p>
 * 
 * @see OptionType
 * @param <T>
 *          The type of values.
 */

@EqualityStructural public final class Some<T> implements OptionType<T>
{
  static <T> Some<T> some(
    final T x)
  {
    return new Some<T>(x);
  }

  private final T x;

  private Some(
    final T in_x)
  {
    this.x = NullCheck.notNull(in_x, "Some value");
  }

  @Override public <U> U accept(
    final OptionVisitorType<T, U> v)
  {
    NullCheck.notNull(v, "Visitor");
    return v.some(this);
  }

  @Override public <U, E extends Throwable> U acceptPartial(
    final OptionPartialVisitorType<T, U, E> v)
    throws E
  {
    NullCheck.notNull(v, "Visitor");
    return v.some(this);
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
    final Some<?> other = (Some<?>) obj;
    return this.x.equals(other.x);
  }

  /**
   * @return The value contained within.
   */

  public T get()
  {
    return this.x;
  }

  @Override public int hashCode()
  {
    return this.x.hashCode();
  }

  @Override public <U> OptionType<U> map(
    final FunctionType<T, U> f)
  {
    NullCheck.notNull(f, "Function");
    return Option.of(f.call(this.x));
  }

  @Override public <U, E extends Throwable> OptionType<U> mapPartial(
    final PartialFunctionType<T, U, E> f)
    throws E
  {
    NullCheck.notNull(f, "Function");
    return Option.of(f.call(this.x));
  }

  @Override public String toString()
  {
    final StringBuilder builder = new StringBuilder();
    builder.append("[Some ");
    builder.append(this.x);
    builder.append("]");
    final String s = builder.toString();
    assert s != null;
    return s;
  }

  @Override public boolean isNone()
  {
    return false;
  }

  @Override public boolean isSome()
  {
    return true;
  }
}
