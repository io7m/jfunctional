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

import com.io7m.jequality.annotations.EqualityReference;
import com.io7m.jnull.Nullable;
import com.io7m.junreachable.UnreachableCodeException;

/**
 * <p>
 * Constructor functions for optional values.
 * </p>
 *
 * @see OptionType
 */

@EqualityReference
public final class Option
{
  private Option()
  {
    throw new UnreachableCodeException();
  }

  /**
   * <p>
   * Return {@link None}.
   * </p>
   *
   * @param <T> The type of values
   *
   * @return An optional value
   */

  public static <T> OptionType<T> none()
  {
    return None.none();
  }

  /**
   * <p>
   * If {@code x == null}, return {@link None}, else return {@link Some}
   * containing {@code x}.
   * </p>
   *
   * @param <T> The type of values
   * @param x   The value
   *
   * @return An optional value
   */

  public static <T> OptionType<T> of(
    final @Nullable T x)
  {
    if (x == null) {
      return None.none();
    }

    return Some.some(x);
  }

  /**
   * <p>
   * Return {@link Some} containing {@code x}.
   * </p>
   *
   * @param <T> The type of values
   * @param x   The value
   *
   * @return An optional value
   */

  public static <T> OptionType<T> some(
    final T x)
  {
    return Some.some(x);
  }
}
