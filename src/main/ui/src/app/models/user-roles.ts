export interface UserRoleModel{
    id: number;
    name: string;
}

export const userRoles = {
    user: {id: 1, name: 'ROLE_USER'},
    manager: {id: 2, name: 'ROLE_MODERATOR'},
    admin: {id: 3, name: 'ROLE_ADMIN'}
};

export interface User {
    id: string;
    username: string;
    firstname: string;
    lastname: string;
    roles: UserRoleModel[];
    email: string;
    password: string;
    role?: any;
    baseSalary: string;
}

export interface SalaryModel {
    fullname: string;
    salaryAmount: number;
}
