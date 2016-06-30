import { createStore, combineReducers, applyMiddleware,compose  } from 'redux'
import {devTools, persistState} from 'redux-devtools';
import {DevTools, DebugPanel, LogMonitor} from 'redux-devtools/lib/react';
import thunkMiddleware from 'redux-thunk'

import rootReducer from '../reducers'

/*logger*/
const logger = store => next => action => {
  // console.log('logger开始', action)

  let result = next(action)
  // console.log('logger结束，next state', store.getState())
  return result
}

/*crashReporter*/
const crashReporter = store => next => action => {
  // console.log('crashReporter开始', action)
  try {
    return next(action)
  } catch (err) {
    console.error('Caught an exception!', err)
    
    Raven.captureException(err, {
      extra: {
        action,
        state: store.getState()
      }
    })
    throw err
  }finally{
      // console.log('crashReporter结束，next state',  store.getState())
  }
}


const finalCreateStore = __DEVTOOLS ? compose(
  // Provides support for DevTools:
  devTools(),
  // Lets you write ?debug_session=<name> in address bar to persist debug sessions
  persistState(window.location.href.match(/[?&]debug_session=([^&]+)\b/))
)(createStore) : createStore;

export default function configureStore(initialState) {

  let createStoreWithMiddleware = applyMiddleware(
    thunkMiddleware,
    logger, 
    crashReporter
    )(finalCreateStore)

  const store = createStoreWithMiddleware(rootReducer,initialState)

  if (module.hot) {
    // Enable Webpack hot module replacement for reducers
    module.hot.accept('../reducers', () => {
      const nextReducer = require('../reducers')
      store.replaceReducer(nextReducer)
    })
  }

  return store
}
