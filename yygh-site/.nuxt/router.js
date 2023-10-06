import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _0788ce62 = () => interopDefault(import('../pages/index.vue' /* webpackChunkName: "pages/index" */))
const _75d569ad = () => interopDefault(import('../pages/hosp/detail/_hoscode.vue' /* webpackChunkName: "pages/hosp/detail/_hoscode" */))
const _1eec02f4 = () => interopDefault(import('../pages/hosp/notice/_hoscode.vue' /* webpackChunkName: "pages/hosp/notice/_hoscode" */))
const _489b79d6 = () => interopDefault(import('../pages/hosp/_hoscode.vue' /* webpackChunkName: "pages/hosp/_hoscode" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/",
    component: _0788ce62,
    name: "index"
  }, {
    path: "/hosp/detail/:hoscode?",
    component: _75d569ad,
    name: "hosp-detail-hoscode"
  }, {
    path: "/hosp/notice/:hoscode?",
    component: _1eec02f4,
    name: "hosp-notice-hoscode"
  }, {
    path: "/hosp/:hoscode?",
    component: _489b79d6,
    name: "hosp-hoscode"
  }],

  fallback: false
}

export function createRouter (ssrContext, config) {
  const base = (config._app && config._app.basePath) || routerOptions.base
  const router = new Router({ ...routerOptions, base  })

  // TODO: remove in Nuxt 3
  const originalPush = router.push
  router.push = function push (location, onComplete = emptyFn, onAbort) {
    return originalPush.call(this, location, onComplete, onAbort)
  }

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    return resolve(to, current, append)
  }

  return router
}
