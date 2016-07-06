import 'babel-core/polyfill'
import React from 'react'
import {render } from 'react-dom'
import {Provider } from 'react-redux'
import {Router, Route } from 'react-router'
import {createHistory}  from 'history'
import {syncReduxAndRouter } from 'redux-simple-router'
import {DevTools, DebugPanel, LogMonitor } from 'redux-devtools/lib/react'

/**
 * 应用的store和action
 */
import configureStore from './store/configureStore';

/**
 * load css , less
 */
import './resource/css/spa.less';

/**
 * 获取store
 */
const store = configureStore();

/**
 * 创建history对象，用于前端路由控制
 */
const history = createHistory();
/**
 * 同步redux和react-router
 */
syncReduxAndRouter(history, store)

/**
 * application root component.
 */
let App = React.createClass({
    render: function () {
        //多语言文件通过路由来控制请求哪个语种
        let language = location.pathname.split('/')[1];
        return (<div>
                    <Provider store={store}>
                        <Router history={history}>
                            <Route path="/(:languages)" getComponent={(location, cb) => {
                                    require.ensure([], function (require) {
                                        cb(null, require('./containers/spa'))
                                    })
                              }}>
                                <Route path="login" getComponents={(location, cb) => {
                                    //登陆router
                                    require.ensure([], function (require) {
                                        cb(null, {content: require('./containers/'+language+'/login/login')})
                                    })
                                }}/>
                                <Route path="page/index" getComponents={(location, cb) => {
                                    //首页router
                                    require.ensure([], function (require) {
                                        cb(null, {content: require('./containers/'+language+'/page/index')})
                                    })
                                }}/>
                           </Route>
                        </Router>
                    </Provider>

                    {
                        __DEVTOOLS &&
                        <DebugPanel top left bottom>
                            <DevTools store={store} monitor={LogMonitor}/>
                        </DebugPanel>
                    }
                </div>)
    }
});

// 渲染整个组件
render(<App /> , document.getElementById('root'));

