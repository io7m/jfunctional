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

/**
 * <p> Non-dependent binary partial function type. </p>
 *
 * @param <A> The type of the first domain
 * @param <B> The type of the second domain
 * @param <C> The type of the codomain
 * @param <E> The type of exceptions raised
 *
 * @since 1.2.0
 */

public interface PartialBiFunctionType<A, B, C, E extends Throwable>
{
  /**
   * Evaluate the function with the given argument.
   *
   * @param x A value of {@code A}
   * @param y A value of {@code B}
   *
   * @return A value of type {@code C}
   *
   * @throws E If required
   */

  C call(
    A x,
    B y)
    throws E;
}
