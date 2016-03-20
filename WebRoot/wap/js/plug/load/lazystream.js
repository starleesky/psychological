/**
 * ```js
 *  var LazyStream = require('plug/load/lazystream')
 *
 *  var stream = new LazyStream('.lazy-stream', {
 *    page: 1, // The base index for backend, defaults to 1
 *    plUrl: './api/pl.php', // stream page endpoint
 *    paramFormater: function(n) { return {page: n} }, // filter function for customize api params
 *    errorText: '<p>Error, Click to try again</p>',
 *    noAnyMore: 'No any more content',
 *    loadingClass: 'loading',
 *    loadingText: '<div>Data loading...</div>'
 *  })
 * ```
 */
define(function(require, exports, module) {
    'use strict';

    var $ = require('jquery');
    var util = require('../util');
    var io = require('../io/request');
    var Lazyload = require('./lazyload_dt');

    // Implements a stream loader
    Lazyload.define('stream', function(el, src, settings, next) {
        var self = this
          , $box = $(el)
          , pageletURL = settings.plUrl
          , param = settings.paramFormater(settings.page)
          , dataFilter = settings.dataFilter || function(o) { return o.data; }
        io.jsonp(pageletURL, param, function(res) {
            if (!+res.error) {
                var html = dataFilter(res);
                if (html) {
                    try {
                        html = unescape(html);
                    } catch (e) {}
                } else {
                    html = settings.endText || ''
                }
                $box.html(html);
            }
        })
        .always(function(res) {
            var data = res.data;
            if ( !data || +(data.error || 0) !== 0 ) { // error
                next(data);
                var error = '<div class="stream-error">' + (settings.errorText || res.msg) + '</div>'
                $box.html(error).off('click').on('click', function(e) {
                    e.preventDefault();
                    self.update();
                });
            }
            else {
                next(null, data);
            }
        });
    });

    /** @constructor */
    var LazyStream = function(container, options) {
        var self = this;

        options = options || {};
        container = $(container);

        if (!options.plUrl) {
            throw 'Init lazystream failed, The pagelet api `plUrl` not defined';
        }

        // merge defaults
        options = $.extend(true, {
            plUrl: '',  // The endpoint api for stream seeking
            page: 1,    // The base index for backend, defaults to 1
            paramFormater: function(n) { return {page: n} }, // A filter function for customize api params
            errorText: '',
            noAnyMore: ''
        }, options);

        options.type = 'stream';
        options.page = +options.page || 1;

        var loadingClass = options.loadingClass;
        var createLoader = function(page, defaultText) {
            defaultText = defaultText || options.loadingText;
            return $('<div class="' + loadingClass + '" data-page="' + page + '">' + defaultText + '</div>')
        };

        if (loadingClass) {
            container.find('.' + loadingClass).remove();
        }

        // prepare for the fist trunk load
        var firstLoader = createLoader(options.page);
        container.append(firstLoader);

        Lazyload.apply(self, [firstLoader, options]);

        self.on('lazyItemReady', function(el, origin, response) {
            if (el.firstChild) {
                // Add next loader
                var loader = createLoader(++self._.page);
                $(el).after(loader);
                self.add(loader);
            } else {
                // EOF
                var noAnyMore = options.noAnyMore;
                if (noAnyMore) $(el).html(noAnyMore);
            }
        });
    };

    util.inherits(LazyStream, Lazyload);

    module.exports = LazyStream;
});
