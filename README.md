[![Build Status](https://travis-ci.org/radeex/amp-clj.png)](https://travis-ci.org/radeex/amp-clj)

# amp-clj

An AMP implementation in Clojure.

## What is AMP?

AMP is the Asynchronous Messaging Protocol. It's a protocol that was invented
by the people who made Twisted. It's a simple request/response RPC mechanism
based on length-prefixed key/value pairs.

Protocol web site: http://amp-protocol.net/

Twisted implementation (see module docstring):
https://github.com/twisted/twisted/blob/trunk/twisted/protocols/amp.py

## Why did you do this?

I did it to learn Clojure -- specifically to learn about doing async I/O and
parsing and generating protocols with it.

This implementation uses Zach Tellman's fabulous
[Gloss](http://github.com/ztellman/gloss) and
[Aleph](http://github.com/ztellman/aleph) libraries. The Aleph part is optional
-- you can use the basic language parser/builder components without actually
using Aleph for your I/O.

## Usage

FIXME: write

## License

The MIT License (MIT)

Copyright (C) 2013 Christopher Armstrong

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

