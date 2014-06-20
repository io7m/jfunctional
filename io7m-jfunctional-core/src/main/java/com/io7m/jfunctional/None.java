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
 * No value.
 * </p>
 * 
 * @see OptionType
 * @param <T>
 *          The type of values
 */

@EqualityStructural public final class None<T> implements OptionType<T>
{
  private static final long    serialVersionUID = -8005083033264721505L;
  private static final None<?> NONE             = new None<Object>();
  private static final int     NONE_HASH_CODE   = 0xFACECAFE;

  @SuppressWarnings("unchecked") static <T> OptionType<T> none()
  {
    return (OptionType<T>) None.NONE;
  }

  private None()
  {

  }

  @Override public <U> U accept(
    final OptionVisitorType<T, U> v)
  {
    NullCheck.notNull(v, "Visitor");
    return v.none(this);
  }

  @Override public <U, E extends Throwable> U acceptPartial(
    final OptionPartialVisitorType<T, U, E> v)
    throws E
  {
    NullCheck.notNull(v, "Visitor");
    return v.none(this);
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
    return true;
  }

  @Override public int hashCode()
  {
    return None.NONE_HASH_CODE;
  }

  @Override public <U> OptionType<U> map(
    final FunctionType<T, U> f)
  {
    NullCheck.notNull(f, "Function");
    return None.none();
  }

  @Override public <U, E extends Throwable> OptionType<U> mapPartial(
    final PartialFunctionType<T, U, E> f)
    throws E
  {
    NullCheck.notNull(f, "Function");
    return None.none();
  }

  @Override public String toString()
  {
    return "[None]";
  }

  @Override public boolean isNone()
  {
    return true;
  }

  @Override public boolean isSome()
  {
    return false;
  }
}
