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

import com.io7m.jequality.annotations.EqualityReference;
import com.io7m.junreachable.UnreachableCodeException;

/**
 * <p>
 * Constructor functions for computations that can fail.
 * </p>
 *
 * @see TryType
 */

@EqualityReference public final class Try
{
  /**
   * Converge the success and failure cases on a common supertype.
   *
   * @param t
   *          A computation
   * @return The success or failure case as a value of a common supertype
   *
   * @param <F>
   *          The type of failure values.
   * @param <S>
   *          The type of success values.
   * @param <R>
   *          The common supertype.
   * @since 1.1.0
   */

  public static <R, F extends R, S extends R> R converge(
    final TryType<F, S> t)
  {
    return t.accept(new TryVisitorType<F, S, R>() {
      @Override public R failure(
        final Failure<F, S> f)
      {
        return f.get();
      }

      @Override public R success(
        final Success<F, S> s)
      {
        return s.get();
      }
    });
  }

  /**
   * Fail trivially, yielding <code>x</code>.
   *
   * @param x
   *          The result
   * @return A computation that yields <code>x</code>.
   * @param <F>
   *          The type of failure values.
   * @param <S>
   *          The type of success values.
   */

  public static <F, S> TryType<F, S> failure(
    final F x)
  {
    return Failure.failure(x);
  }

  /**
   * Succeed trivially, yielding <code>x</code>.
   *
   * @param x
   *          The result
   * @return A computation that yields <code>x</code>.
   * @param <F>
   *          The type of failure values.
   * @param <S>
   *          The type of success values.
   */

  public static <F, S> TryType<F, S> success(
    final S x)
  {
    return Success.success(x);
  }

  private Try()
  {
    throw new UnreachableCodeException();
  }
}
