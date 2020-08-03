export class RoutingConstants {
    static readonly ROUTE_LOGIN = 'user/login';
    static readonly ROUTE_VIEW = 'view';
    static readonly ROUTE_DASHBOARD = 'view/board';
    static readonly ROUTE_USERS = 'view/usermgmt/growsariUser'

    static getRoute(routeName) : string {
        return '/' + routeName;
    }
}