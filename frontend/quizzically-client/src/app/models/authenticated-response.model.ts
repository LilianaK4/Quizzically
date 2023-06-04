export interface AuthenticatedResponse {
  authenticationToken: string;
  refreshToken: string;
  expiresAt: Date;
  username: string;
}
